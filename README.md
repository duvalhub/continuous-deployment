# Continous Deployment
This pipeline is meant to be launched automatically by version control (github) on every commit in order to build a new version and deploy it into the appropriate environment.
It follows the [successful Git branching model](https://nvie.com/posts/a-successful-git-branching-model/) and can deploy into three environments: dev, uat and prod.

It makes used of [shared library](https://github.com/duvalhub/continuous-deployment-shared-library) for common Jenkins functions and configurations.
