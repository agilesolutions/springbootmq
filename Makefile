# Other contants
NAMESPACE=demo
setup: cluster/prepare skaffold jdk cluster/run
##############################
# kubectl work               #
##############################
cluster/prepare:
	@kubectl create namespace $(NAMESPACE) || true



##############################
# install jdk                #
##############################
jdk:
	@wget https://github.com/AdoptOpenJDK/openjdk8-binaries/releases/download/jdk8u232-b09/OpenJDK8U-jdk_x64_linux_hotspot_8u232b09.tar.gz
	@tar -xf OpenJDK8U-jdk_x64_linux_hotspot_8u232b09.tar.gz
	@export PATH=$PWD/jdk8u232-b09/bin:$PATH
	@java -version



##############################
# install skaffold           #
##############################
skaffold:
	@curl -Lo skaffold https://storage.googleapis.com/skaffold/releases/v1.2.0/skaffold-linux-amd64
	@chmod +x skaffold 
	@sudo mv skaffold /usr/local/bin

##############################
# Run stack                  #
##############################
cluster/run:
	@kubectl apply -f app/all.yaml || true
	@kubectl apply -f mq/all.yaml || true
#	@kubectl apply -f mysql/all.yaml || true

##############################
# Cleanup stack              #
##############################
cleanup:
	@kubectl delete -f mq/all.yaml || true
#	@kubectl delete -f mysql/all.yaml || true
	@kubectl delete -f app/all.yaml || true
	
watch:
	@watch kubectl get all -n demo || true