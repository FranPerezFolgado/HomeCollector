docker_path="./docker/"
mongo-up:
	docker compose -f $(docker_path)docker-compose.mongo.yml up -d

mongo-down:
	docker compose -f $(docker_path)docker-compose.mongo.yml down

mongo-restart:
	mongo-down mongo-up
