#!/bin/bash

# Function to run JMeter tests
run_jmeter_test() {
  local test_number=$1
  local jmx_file=$2

  echo "Running JMeter tests number $test_number..."

  docker run -v "$(pwd)/jmeter:/jmeter" -v "$(pwd)/results:/results" \
    --name jmeter -it --rm --network host \
    justb4/jmeter -n -t /jmeter/$jmx_file -l /results/test_${test_number}.jtl

  echo "JMeter test completed."
}

# Run JMeter tests with different configurations
run_jmeter_test 1 test.jmx # cluster:node_cpu:sum_rate5m - PROMETHEUS
