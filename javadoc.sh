#!/bin/bash

javadoc \
    -sourcepath src/                                        \
    -cp src/musichub/main       src/musichub/main/*         \
    -cp src/musichub/util       src/musichub/util/*         \
    -cp src/musichub/business   src/musichub/business/*     \
    -d doc/