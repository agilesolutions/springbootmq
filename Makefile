# Other contants
NAMESPACE=demo
setup: cluster/prepare cluster/run
##############################
# kubectl work               #
##############################
cluster/prepare:
	@kubectl create namespace $(NAMESPACE) || true
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