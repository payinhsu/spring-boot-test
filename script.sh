!#/bin/bash
if [ " -z ${SPRING_PROFILES_ACTIVE}" ]
then
	export SPRING_PROFILES_ACTIVE=dev
fi
echo “SPRING_PROFILES_ACTIVE=${SPRING_PROFILES_ACTIVE}”

export APP_LOG_FOLDER=/Users/peterhsu/IdeaProjects/logger

export SPRING_APPLICATION_NAME=springBootTest
export LOGGING_LEVEL=INFO,DEBUG