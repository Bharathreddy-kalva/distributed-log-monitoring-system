import random
import time
import uuid
from datetime import datetime, timezone

import requests

INGESTION_API_URL = "http://localhost:8081/api/logs"

SERVICES = [
    "auth-service",
    "payment-service",
    "order-service",
    "notification-service",
    "inventory-service"
]

HOSTS = [
    "server-1",
    "server-2",
    "server-3"
]

ENVIRONMENTS = [
    "dev",
    "staging",
    "prod"
]

LOG_TEMPLATES = {
    "INFO": [
        "User login successful",
        "Order created successfully",
        "Notification email sent",
        "Inventory updated",
        "Payment processed successfully"
    ],
    "WARN": [
        "High response time detected",
        "Retry attempt for downstream service",
        "Inventory running low",
        "Delayed email delivery",
        "Unusual traffic spike detected"
    ],
    "ERROR": [
        "Database timeout",
        "Payment gateway timeout",
        "Failed to send notification",
        "Authentication token expired",
        "Order processing failed"
    ]
}

LEVEL_WEIGHTS = ["INFO", "INFO", "INFO", "WARN", "WARN", "ERROR"]


def generate_log():
    level = random.choice(LEVEL_WEIGHTS)
    service_name = random.choice(SERVICES)
    host = random.choice(HOSTS)
    environment = random.choice(ENVIRONMENTS)
    message = random.choice(LOG_TEMPLATES[level])

    return {
        "timestamp": datetime.now(timezone.utc).isoformat().replace("+00:00", "Z"),
        "serviceName": service_name,
        "host": host,
        "level": level,
        "message": message,
        "traceId": str(uuid.uuid4())[:12],
        "environment": environment
    }


def send_log(log_event):
    try:
        response = requests.post(INGESTION_API_URL, json=log_event, timeout=5)
        print(f"[{response.status_code}] Sent log: {log_event}")
    except requests.RequestException as exc:
        print(f"[ERROR] Failed to send log: {exc}")


def main():
    print("Starting log producer simulator...")
    print(f"Sending logs to: {INGESTION_API_URL}")

    while True:
        log_event = generate_log()
        send_log(log_event)
        time.sleep(random.uniform(1, 3))


if __name__ == "__main__":
    main()
