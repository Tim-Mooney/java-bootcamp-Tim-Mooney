import type { Customer } from '../types/customer'
import { http } from './http'

export const customersApi = {
  list(signal?: AbortSignal): Promise<Customer[]> {
    return http<Customer[]>('/api/customers', {}, signal)
  },
  get(customerId: string, signal?: AbortSignal): Promise<Customer> {
    return http<Customer>(`/api/customers/${customerId}`, {}, signal)
  },
  create(customer: Omit<Customer, 'id'>, signal?: AbortSignal): Promise<Customer> {
    return http<Customer>(
        '/api/customers',
        { method: 'POST', body: JSON.stringify(customer) },
        signal,
    )
  },
}
