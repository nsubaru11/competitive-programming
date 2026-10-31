cmake_minimum_required(VERSION 3.30)
project(ABC395)

set(CMAKE_CXX_STANDARD 23)

file(GLOB PROBLEMS "*.cpp")
foreach(problem ${PROBLEMS})
    get_filename_component(name ${problem} NAME_WE)
    message(STATUS "Creating target for ${name}")
    add_executable(${name} ${problem}
            A.cpp
            A.h
            B.cpp
            B.h
            C.cpp
            C.h
            D.cpp
            D.h
            test.cpp
            test.h
    )
endforeach()
