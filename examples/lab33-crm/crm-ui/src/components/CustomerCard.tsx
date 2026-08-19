import type { Customer } from '../types/customer'
import { StatusBadge } from './StatusBadge'

export function CustomerCard({
  customer,
  onEdit,
}: {
  customer: Customer
  onEdit: (customerId: string) => void
}) {
  return (
    <article className="card" data-testid={`card-${customer.customerId}`}>
      <p>{customer.fullName}</p>
      <p>{customer.email}</p>
      <StatusBadge status={customer.status} />
      <button type="button" onClick={() => onEdit(customer.customerId)}>
        Edit
      </button>
    </article>
  )
}
