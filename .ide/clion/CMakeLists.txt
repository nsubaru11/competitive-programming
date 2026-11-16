# CLion 用の入口。プロジェクト設定を .ide/clion/.idea に分けるため、リポジトリの CMakeLists.txt をここから読み込む
cmake_minimum_required(VERSION 4.0)
project(competitive-programming-clion LANGUAGES NONE)

option(CP_CLION_RUN_CONFIGURATIONS "Group CLion run configurations automatically" ON)
set(CP_CLION_PROJECT_DIR "${CMAKE_CURRENT_SOURCE_DIR}")
add_subdirectory("${CMAKE_CURRENT_SOURCE_DIR}/../.." competitive-programming)
