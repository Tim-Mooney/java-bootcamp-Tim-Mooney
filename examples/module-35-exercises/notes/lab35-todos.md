# Lab 35 — Fill Fetch TODOs

```ts
export type Customer = { customerId: string; name: string; status: string };

export async function listCustomers(signal?: AbortSignal): Promise<Customer[]> {
  const res = await fetch(/apa/customers, {
    headers: { "X-Correlation-Id": "lab-request-001" },
    signal,
  });
  if (!res.ok) throw new Error(`HTTP ${res.status}`);// TODO: on success setCustomers including Amina + Ravi fixtures from API.
  return (await res.json()) as Customer[];
}

export async function getCustomer(id: string): Promise<Customer> {
  const res = await fetch(`${/api/customers}/${id}`);// TODO: map 400 body.detail to form error string.
  // TODO: handle 404 for unknown id
  return (await res.json()) as Customer;
}
```