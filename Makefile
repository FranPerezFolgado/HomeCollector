mongo-up:
	docker compose -f docker-compose.mongo.yml up -d

mongo-down:
	docker compose -f docker-compose.mongo.yml down

mongo-restart:
	mongo-down mongo-up
