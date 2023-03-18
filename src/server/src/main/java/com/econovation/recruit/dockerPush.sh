sudo docker login
sudo docker build --build-arg DEPENDENCY=build/dependency -t blackbean99/econo-recruit .
sudo docker push blackbean99/econo-recruit