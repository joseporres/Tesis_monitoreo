# Tesis_monitoreo

## Pasos a seguir
Move2kube al ser una apolicacion open source, relativamente nueva, actualmente tiene pequeños proplemas con ciertos lenguajes por lo que tuve que modificar un poco el output brindado por la herramienta de migracion para que funcione todo correctamente. Esto se encuentra en my project.

Para empezar, se tiene que tener instalado minikube y kubectl de antemano.

1. Iniciar tu cluster de kubernetes con minikube
```
minikube start
```

2. Se debería correr el script de m2ktransform_metrics, que realiza la migracion de la aplicacion y levanta el sistema de monitoreo automaticamente.
```
./m2ktransform_metrics.sh src
```
Para esta aplicacion no es necesario correr ese script, a no ser que se tenga previo conocimiento del funcionamiento de move2kube, ya que con la aplicacion que estamos probando, move2kube genera una imagen errada y se tuvo que modificar el Dockerfile generado por move2kube para que funcione correctamente.

Por esta razon solo se correra el script deploy_metrics, ya que los archivos de configuracion de kubernetes ya se encuentran en la carpeta myproject.
```
./deploy_metrics.sh
```
Esto levantara todo el sistema de monitoreo.

Ahora solo falta desplegar la aplicacion migrada al cluster de kubernetes. Cabe resaltar que move2kube solo realiza la migracion mas no el despliegue de la aplicacion.

Por esta razon solamente correremos el siguiente comando para desplegar la aplicación migrada:
```
kubectl apply -f myproject/deploy/yamls
```

Con esto ya se tendria todo el sistema de monitoreo de recursos del cluster.

Despues solo seria necesario crear los queries necesarios usando prometheus y utilizarlos en grafana para graficarlos o mostrarlos en un dashboard mas user friendly.