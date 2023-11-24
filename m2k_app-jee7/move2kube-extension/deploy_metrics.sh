#!/bin/bash

# Check if Minikube is running
if [ "$(minikube status | grep 'minikube: Stopped')" ]; then
  echo "Minikube is not running. Starting Minikube..."
  minikube start
fi

# # Check if a source directory is provided as an argument
# if [ "$#" -ne 1 ]; then
#   echo "Usage: $0 <src-directory>"
#   exit 1
# fi

# src_directory="$1"

# Set the Kubernetes context to the Minikube cluster
kubectl config use-context minikube

# Step 2: Add the Prometheus Community Helm repository
echo "Adding Prometheus Community Helm repository"
helm repo add prometheus-community https://prometheus-community.github.io/helm-charts

# Step 3: Update the Helm repositories
echo "Updating Helm repositories"
helm repo update

# Step 4: Install the kube-prometheus-stack using Helm
echo "Installing kube-prometheus-stack"
helm install kube-prometheus-stack prometheus-community/kube-prometheus-stack

# Step 5: Apply the customized ServiceMonitor for the application
echo "Applying the ServiceMonitor for the application"
kubectl apply -f service-monitor/service_monitor.yaml

echo "Script execution complete."