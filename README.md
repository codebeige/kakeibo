# kakeibo

Manage shared budgets online.

## Getting started

### Prerequisites

[Docker] and [Docker Compose] have to be available on your system. For detailed
[installation instructions] please refer to the [Docker Documentation].

[Docker]: https://www.docker.com
[Docker Compose]: https://docs.docker.com/compose/overview/
[installation instructions]: https://docs.docker.com/engine/installation/
[Docker Documentation]: https://docs.docker.com

### Development

Build and run the interactive development environment linked to the local
project directory:

```sh
docker-compose up
```

### Testing

Run the project's tests:

```sh
docker-compose run --rm boot test
```

## Deployment

Build an uberjar from the project:

```sh
docker-compose run --rm boot build
```

Run the uberjar:

```sh
docker-compose run --rm --entrypoint java boot -jar target/kakeibo-0.1.0-SNAPSHOT-standalone.jar
```

---
Copyright © 2017 Tibor Claassen. Distributed under the MIT License.
