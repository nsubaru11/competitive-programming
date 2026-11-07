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
foreach (cpp_directory IN LISTS cpp_directories)
	string(REGEX MATCH "^[^/]+" site "${cpp_directory}")
	file(GLOB sources CONFIGURE_DEPENDS "${CMAKE_CURRENT_SOURCE_DIR}/${cpp_directory}/*.cpp")
	foreach (source IN LISTS sources)
		file(RELATIVE_PATH relative "${CMAKE_CURRENT_SOURCE_DIR}" "${source}")
		string(MAKE_C_IDENTIFIER "solution_${relative}" target)
		add_executable(${target} EXCLUDE_FROM_ALL "${source}")
		cp_configure_solution(${target} ${site})
	endforeach ()
endforeach ()

add_executable(template_cpp EXCLUDE_FROM_ALL templates/cpp/TemplateCode.cpp)
cp_configure_solution(template_cpp atcoder)
