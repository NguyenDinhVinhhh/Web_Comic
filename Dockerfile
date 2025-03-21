FROM ubuntu:latest
LABEL authors="Mac"

ENTRYPOINT ["top", "-b"]