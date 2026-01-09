## Webhook Manager

A centralized webhook system that allows services to emit events and clients to subscribe URLs to those events, automatically delivering payloads when events occur.

## How It Works

### Client Side (Consumers)

Clients register:
- A **URL endpoint**
- One or more **events** they want to receive

When a subscribed event occurs, the Webhook Manager sends the corresponding payload to the registered URL.

---

### Server Side (Event Producers)

Services register themselves with the Webhook Manager by providing:
- Their **service URL**
- The **event name(s)** they can emit

When an event occurs, the service notifies the Webhook Manager with the event data.

---

### Webhook Manager Responsibilities

- Maintain a mapping between **events** and **subscribed client URLs**
- Receive event notifications from **producer services**
- Dispatch payloads to **all client URLs subscribed** to the triggered event


## Diagram
(images/webhookDiagram.png)
