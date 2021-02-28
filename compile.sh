#!/bin/bash

javac \
    -sourcepath src/                        \
    -cp bin/ src/musichub/main/Main.java    \
    -d bin/
