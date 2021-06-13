sum(jvm_memory_used_bytes) by (job)
sum(process_cpu_usage) by (job)
http_server_requests_seconds_sum{uri="/search"}
sum(jvm_gc_pause_seconds_sum) by (job)

.\prometheus.exe --config.file=prometheus.yml --web.enable-admin-api
