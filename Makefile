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
	@kubectl apply -f mq/all.yaml || true
	@kubectl apply -f oracle/all.yaml || true
	@kubectl apply -f application/all.yaml || true

##############################
# Cleanup stack              #
##############################
cluster/run:
	@kubectl delete -f mq/all.yaml || true
	@kubectl delete -f oracle/all.yaml || true
	@kubectl delete -f application/all.yaml || true