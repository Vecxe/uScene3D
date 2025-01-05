# Define a versão mínima do CMake
cmake_minimum_required(VERSION 3.10)

# Nome do projeto
project("hdri_to_cubemap")

# Adicione a biblioteca nativa
add_library(
    hdri_to_cubemap
    SHARED
    converter.cpp         # Implementação principal
)

# Inclua os diretórios de cabeçalho
target_include_directories(
    hdri_to_cubemap
    PRIVATE
    ${CMAKE_SOURCE_DIR}   # Diretório atual com os headers
)

# Adicione suporte ao padrão C++17
set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

# Encontre bibliotecas do sistema
find_library(
    log-lib
    log
)

# Vincule as bibliotecas necessárias
target_link_libraries(
    hdri_to_cubemap
    ${log-lib}
)