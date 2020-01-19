# Other contants
NAMESPACE=demo
setup: cluster/prepare skaffold cluster/run
##############################
# kubectl work               #
##############################
cluster/prepare:
	@kubectl create namespace $(NAMESPACE) || true

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
	@kubectl apply -f application/all.yaml || true
	@kubectl apply -f mq/all.yaml || true
#	@kubectl apply -f mysql/all.yaml || true

##############################
# Cleanup stack              #
##############################
cleanup:
	@kubectl delete -f mq/all.yaml || true
#	@kubectl delete -f mysql/all.yaml || true
	@kubectl delete -f application/all.yaml || true
	
watch:
	@watch kubectl get all -n demo || true