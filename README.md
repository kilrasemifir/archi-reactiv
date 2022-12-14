# Architecture Reactive

## Présentation

Ce projet est une exemple d'une stack microservice avec une architecture réactive.

Ce projet est composé de deux micro services qui communiquent entre eux grâce à Kafka.

### Microservices

- **commandes** : Microservice qui permet de gérer les commandes des utilisateurs.
  - Disponible sur le port 8080
- **stocks** : Microservice qui permet de gérer le stock des produits.
  - Disponible sur le port 8081

## Prérequis

- Java 11+
- Docker
- Docker-compose

## Lancement

### Lancement de la stack

```bash
docker-compose up -d
```

## Utilisation

Ajouter une commande avec des lignes de commandes

```http
POST http://localhost:8080/api/commandes
Content-Type: application/json

{
  "lignes": [
    {
      "produitId": 1,
      "quantite": 2
    },
    {
      "produitId": 2,
      "quantite": 1
    }
  ]
}
```

Vous pouvez voir qu'une nouvelle line de stock est créée dans le microservice stock.

```http
GET http://localhost:8081/api/stocks
```

## Architecture

![Architecture](./assets/archi_1.svg)