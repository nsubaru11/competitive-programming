cmake_minimum_required(VERSION 4.0)
project(competitive-programming LANGUAGES CXX)

set(CMAKE_CXX_STANDARD 23)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_CXX_EXTENSIONS ON)
set(CMAKE_EXPORT_COMPILE_COMMANDS ON)
set_property(CACHE CMAKE_BUILD_TYPE PROPERTY STRINGS Debug Run)
set(CMAKE_CXX_FLAGS_RUN "" CACHE STRING "C++ flags for local runs")

include(cmake/LocalOptions.cmake)

find_package(Python3 COMPONENTS Interpreter REQUIRED)
execute_process(
	COMMAND "${Python3_EXECUTABLE}"
	"${CMAKE_CURRENT_SOURCE_DIR}/scripts/list-cpp-directories.py"
	"${CMAKE_CURRENT_SOURCE_DIR}"
	OUTPUT_VARIABLE cpp_directories
	OUTPUT_STRIP_TRAILING_WHITESPACE
	COMMAND_ERROR_IS_FATAL ANY
)
set_property(DIRECTORY APPEND PROPERTY CMAKE_CONFIGURE_DEPENDS
	"${CMAKE_CURRENT_SOURCE_DIR}/scripts/list-cpp-directories.py")
string(REPLACE "\r\n" "\n" cpp_directories "${cpp_directories}")
string(REPLACE "\n" ";" cpp_directories "${cpp_directories}")

function(cp_solution_target relative output)
	string(REGEX REPLACE "/src/cpp/[^/]+\\.cpp$" "" contest "${relative}")
	get_filename_component(problem "${relative}" NAME)
	string(REGEX REPLACE "\\.cpp$" "" problem "${problem}")

	# コンテスト名や問題名に含まれる分類は、サイトごとの構成に合わせて省く。
	if (contest MATCHES "^atcoder/(abc|agc|ahc|arc|awc)/([0-9]+-[0-9]+/)?([^/]+)$")
		set(category "${CMAKE_MATCH_1}")
		set(contest_name "${CMAKE_MATCH_3}")
		if (contest_name MATCHES "^${category}[0-9]+$")
			set(contest "atcoder/${contest_name}")
		endif ()
	elseif (contest MATCHES "^atcoder/other/[^/]+$")
		string(REGEX REPLACE "^atcoder/other/" "atcoder/" contest "${contest}")
	elseif (contest MATCHES "^codeforces/div[1-4]/round[0-9]+$")
		string(REGEX REPLACE "^codeforces/div[1-4]/" "codeforces/" contest "${contest}")
	elseif (contest MATCHES "^icpc/jag/prelim/jagprelim[0-9]+[^/]*$")
		string(REGEX REPLACE "^icpc/jag/prelim/" "icpc/" contest "${contest}")
	elseif (contest MATCHES "^icpc/prelim/prelim[0-9]+[^/]*$")
		string(REGEX REPLACE "^icpc/prelim/" "icpc/" contest "${contest}")
	elseif (contest MATCHES "^paiza/skill-check/([a-ds])$")
		string(TOUPPER "${CMAKE_MATCH_1}" rank)
		if (problem MATCHES "^${rank}[0-9]+$")
			set(contest "paiza/skill-check")
		endif ()
	endif ()

	string(MAKE_C_IDENTIFIER "${contest}/${problem}" target)
	set(${output} "${target}" PARENT_SCOPE)
	if (ARGC GREATER 2)
		string(REPLACE "/" " / " folder "${contest}")
		set(${ARGV2} "${folder}" PARENT_SCOPE)
	endif ()
endfunction()

set(cp_clion_targets "")
foreach (cpp_directory IN LISTS cpp_directories)
	string(REGEX MATCH "^[^/]+" site "${cpp_directory}")
	file(GLOB sources CONFIGURE_DEPENDS "${CMAKE_CURRENT_SOURCE_DIR}/${cpp_directory}/*.cpp")
	foreach (source IN LISTS sources)
		file(RELATIVE_PATH relative "${CMAKE_CURRENT_SOURCE_DIR}" "${source}")
		cp_solution_target("${relative}" target folder)
		if (TARGET "${target}")
			get_target_property(conflicting_sources "${target}" SOURCES)
			message(FATAL_ERROR "実行ターゲット名 '${target}' が重複しています:\n"
				"  ${conflicting_sources}\n  ${source}")
		endif ()
		add_executable(${target} EXCLUDE_FROM_ALL "${source}")
		cp_configure_solution(${target} ${site})
		string(APPEND cp_clion_targets "${target}\t${folder}\n")
	endforeach ()
endforeach ()

add_executable(template_cpp EXCLUDE_FROM_ALL templates/cpp/Competitive.cpp)
cp_configure_solution(template_cpp atcoder)

if (CP_CLION_RUN_CONFIGURATIONS AND CP_CLION_PROJECT_DIR)
	set(clion_sync_script "${CMAKE_CURRENT_SOURCE_DIR}/scripts/sync-clion-run-configurations.py")
	set_property(DIRECTORY APPEND PROPERTY CMAKE_CONFIGURE_DEPENDS "${clion_sync_script}")
	set(clion_targets_file "${CMAKE_CURRENT_BINARY_DIR}/clion-run-targets.tsv")
	file(WRITE "${clion_targets_file}" "${cp_clion_targets}template_cpp\t\n")
	execute_process(
		COMMAND "${Python3_EXECUTABLE}" "${clion_sync_script}"
		--project-dir "${CP_CLION_PROJECT_DIR}" --project-name "${CMAKE_PROJECT_NAME}"
		--targets "${clion_targets_file}"
		RESULT_VARIABLE clion_sync_status
		OUTPUT_VARIABLE clion_sync_result
		ERROR_VARIABLE clion_sync_error
		OUTPUT_STRIP_TRAILING_WHITESPACE
		ERROR_STRIP_TRAILING_WHITESPACE
	)
	# 実行設定の整理は補助機能なので、失敗してもビルドの構成は止めない。
	if (clion_sync_status EQUAL 0)
		message(STATUS "${clion_sync_result}")
	else ()
		message(WARNING "CLionの実行設定を同期できませんでした:\n${clion_sync_error}")
	endif ()
endif ()
