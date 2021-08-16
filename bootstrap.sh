#/usr/bin/env bash

BLUE=`tput setaf 4`
CYAN=`tput setaf 6`
GREEN=`tput setaf 2`

BOLD=`tput bold`
RESET=`tput sgr0`

print_done() {
    printf "${BOLD}${GREEN}Done! \n${RESET}"
}

printf "\nHewoooooo. This is ${CYAN}boostrap-reagent-project${RESET}.\nGet ready to get a Reagent app in the next 32 seconds!\n\n"
printf "App Name: ${BOLD}${BLUE}"
read NAME

NAME=$(echo $NAME | sed "s/[^[:alnum:]-]//g" | sed -e 's/\(.*\)/\L\1/')

printf "${RESET}\nGreat choice! Working on making it happen...\n\n"

printf "Making $NAME directory... "
mkdir $NAME
print_done

WORKDIR=$PWD
pushd $(mktemp -d ) > /dev/null
    printf "Cloning bootstrap repo... "
    git clone --quiet "https://github.com/Jemoka/bootstrap-reagent-project" rp
    print_done
    printf "Creating target directory... "
    mv ./rp/src/* $WORKDIR/$NAME/
    mv ./rp/src/.gitignore $WORKDIR/$NAME/
    print_done
popd > /dev/null

pushd $NAME > /dev/null
    printf "Moving source directory... "
    mv ./src/__app_name ./src/$NAME/
    print_done
    printf "Setting project name... "
    for FILE in $(find . -type f); do
        echo "$(cat $FILE | sed -e "s/__app_name/$NAME/g")" > $FILE
    done
    print_done
    printf "Fetching dependencies... "
    yarn &> /dev/null
    print_done
    printf "Generating tailwind CSS file... "
    npx tailwindcss -o ./public/css/tailwind.css &> /dev/null
    print_done
    printf "Initializing git repo... "
    git init --quiet 
    git add --all 1>/dev/null 2>/dev/null
    git commit -m "Initial Commit" --quiet 
    print_done
popd > /dev/null
printf "\n"

printf "Alrighty! That's it. \nExecute ${CYAN}cd $NAME${RESET} and ${CYAN}yarn dev${RESET} to get started.\n"
printf "Happy Clojuring! Thanks for bootstrapping Reagent.\n"

printf "\n"

