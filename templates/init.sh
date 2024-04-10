#!/bin/bash

#sh ./createfolderandfile.sh john 25 'John Smith'

#Echo usages: always launch in bash from rootfolder of the project:
# groupid always in the format of one whole word
# artifactid => 1 word or with dashes; only acceptabel format is: custom-spring-boot-starter or customspringbootstarter

# sh ./templates/init.sh groupid1 artifactid1
# sh ./templates/init.sh groupid1 artifactid1 dirs  => dirs is the name of the subdirectory
# sh ./templates/init.sh groupid1 artifactid1 dirs/secondlevel  => dirs is the name of the subdirectory

PS3="Select item please: "

items=("springbootprojecttemplate", "springbootstarterprojecttemplate" );

choice=''

while true; do
    select item in "${items[@]}" Quit
    do
        case $REPLY in
            1) echo "Selected item #$REPLY which means $item"; choice=$item; break 2;;
            2) echo "Selected item #$REPLY which means $item"; choice=$item; break 2;;
#            3) echo "Selected item #$REPLY which means $item"; choice=$item; break 2;;
#            4) echo "Selected item #$REPLY which means $item"; choice=$item; break 2;;
            $((${#items[@]}+1))) echo "We're done!"; break 2;;
            *) echo "Ooops - unknown choice $REPLY"; break;
        esac
    done
done

echo "choice filip is: $choice";


groupid=$1
artifactid=$2
projectfoldername=$artifactid
echo "projectfoldername is: $projectfoldername"

folderpath="$3"

# check for not null
if [[ -n "$3" ]]; then
  echo "You supplied the third parameter";
  echo "folderpath is: $folderpath";
  mkdir -p $folderpath;
#  mkdir -p ../$folderpath;
  echo "folderpath is: $folderpath"
  echo "projectfoldername is: $projectfoldername"
  projectfoldername="./$folderpath/$projectfoldername"
  echo "projectfoldername is: $projectfoldername"
else
  echo "Third parameter not supplied."
  echo "projectfoldername is: $projectfoldername"
  projectfoldername="./$projectfoldername"
#  projectfoldername="../$projectfoldername"
#  echo "projectfoldername is: $projectfoldername"
fi

if [ $choice -eq "springbootprojecttemplate" ]; then

  echo "you choice springbootprojecttemplate"

echo "groupid= $groupid and artifactid= $artifactid and template ./templates/$choice"

cp -R "./templates/$choice" $projectfoldername
#cp -R ./templates/springbootprojecttemplate $projectfoldername
#cp -R artifactidtoreplace $projectfoldername

artifactidwithouthyphens=$(echo $artifactid | tr -d '-')
echo $artifactidwithouthyphens

mainpathjava="$projectfoldername/src/main/java/io/filipvde/"
mainpathresources="$projectfoldername/src/main/resources/"
testpathjava="$projectfoldername/src/test/java/io/filipvde/"
testpathresources="$projectfoldername/src/test/resources/"

foldergroupidnametoreplace="replacegroupidwithscriptvariable"
folderartifactidnametoreplace="artifactidtoreplace"

applicationnametoreplace="ArtifactidtoreplaceApplication"
applicationfilenametoreplace="ArtifactidtoreplaceApplication.java"

testsapplicationnametoreplace="ArtifactidtoreplaceApplicationTests"
testsapplicationfilenametoreplace="ArtifactidtoreplaceApplicationTests.java"



#sed -i 's/GH_PAT_FOR_ACTIONS_TOKEN/${{ secrets.GH_PAT_FOR_ACTIONS_TOKEN }}/g' .github/settings.xml


echo "STEP 1 replace groupid in pom.xml"

sed -i "" "s/replacegroupidwithscriptvariable/$groupid/g" $projectfoldername/pom.xml

echo "STEP 2 replace artifactid in pom.xml"

sed -i "" "s/artifactidtoreplace/$artifactid/g" $projectfoldername/pom.xml

echo "STEP 3 rename the application"

cat $projectfoldername/src/main/java/io/filipvde/replacegroupidwithscriptvariable/artifactidtoreplace/ArtifactidtoreplaceApplication.java

echo "STEP 4"

mv "$projectfoldername/src/main/java/io/filipvde/replacegroupidwithscriptvariable" "$projectfoldername/src/main/java/io/filipvde/$groupid"

echo "STEP 5"

mv "$projectfoldername/src/main/java/io/filipvde/$groupid/artifactidtoreplace" "$projectfoldername/src/main/java/io/filipvde/$groupid/$artifactidwithouthyphens"

echo "STEP 6"

sed -i "" "s/replacegroupidwithscriptvariable/$groupid/g" "$projectfoldername/src/main/java/io/filipvde/$groupid/$artifactidwithouthyphens/ArtifactidtoreplaceApplication.java"

echo "STEP 7"

sed -i "" "s/artifactidtoreplace/$artifactidwithouthyphens/g" "$projectfoldername/src/main/java/io/filipvde/$groupid/$artifactidwithouthyphens/ArtifactidtoreplaceApplication.java"

echo "STEP 8"

sed -i "" "s/ArtifactidtoreplaceApplication/StarterApplication/g" "$projectfoldername/src/main/java/io/filipvde/$groupid/$artifactidwithouthyphens/ArtifactidtoreplaceApplication.java"

echo "STEP 9"

mv "$projectfoldername/src/main/java/io/filipvde/$groupid/$artifactidwithouthyphens/ArtifactidtoreplaceApplication.java" "$projectfoldername/src/main/java/io/filipvde/$groupid/$artifactidwithouthyphens/StarterApplication.java"

echo "STEP 10"

sed -i "" "s/artifactidtoreplace/$artifactid/g" $projectfoldername/src/main/resources/application.yml



# test

echo "STEP 11"

mv "$projectfoldername/src/test/java/io/filipvde/replacegroupidwithscriptvariable" "$projectfoldername/src/test/java/io/filipvde/$groupid"

echo "STEP 12"

mv "$projectfoldername/src/test/java/io/filipvde/$groupid/artifactidtoreplace" "$projectfoldername/src/test/java/io/filipvde/$groupid/$artifactidwithouthyphens"

echo "STEP 13"

sed -i "" "s/replacegroupidwithscriptvariable/$groupid/g" "$projectfoldername/src/test/java/io/filipvde/$groupid/$artifactidwithouthyphens/ArtifactidtoreplaceApplicationTests.java"

echo "STEP 14"

sed -i "" "s/artifactidtoreplace/$artifactidwithouthyphens/g" "$projectfoldername/src/test/java/io/filipvde/$groupid/$artifactidwithouthyphens/ArtifactidtoreplaceApplicationTests.java"

echo "STEP 15"

sed -i "" "s/ArtifactidtoreplaceApplicationTests/StarterApplicationTests/g" "$projectfoldername/src/test/java/io/filipvde/$groupid/$artifactidwithouthyphens/ArtifactidtoreplaceApplicationTests.java"

echo "STEP 16"

mv "$projectfoldername/src/test/java/io/filipvde/$groupid/$artifactidwithouthyphens/ArtifactidtoreplaceApplicationTests.java" "$projectfoldername/src/test/java/io/filipvde/$groupid/$artifactidwithouthyphens/StarterApplicationTests.java"

#-----
elseif [ $choice -eq "springbootstarterprojecttemplate" ]; then

echo "you chose springbootstarterprojecttemplate"

fi