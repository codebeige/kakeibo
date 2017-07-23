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

Automatically run related tests on file changes:

```sh
docker-compose run --rm boot autotest
```

## Deployment

Build and run uberjar:

```sh
docker-compose -f deploy/docker-compose.yml up --build --force-recreate
```

---
Copyright © 2017 Tibor Claassen. Distributed under the MIT License.
