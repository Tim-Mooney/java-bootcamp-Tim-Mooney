import { useState } from 'react'
import { CustomerForm } from './components/CustomerForm'
import { seedCustomers } from './data/seedCustomers'
import type { Customer, CustomerDraft, UiMode } from './types/customer'
import { validateCustomerDraft } from './validation/customerValidation'

const emptyDraft = (): CustomerDraft => ({
  fullName: '',
  email: '',
  status: 'PROSPECT',
})

export default function App() {
  const [customers, setCustomers] = useState<Customer[]>(seedCustomers)
  const [mode, setMode] = useState<UiMode>({ type: 'list' })
  const [draft, setDraft] = useState<CustomerDraft>(emptyDraft())
  const [saving, setSaving] = useState(false)
  const [errors, setErrors] = useState(validateCustomerDraft(emptyDraft()))


  function getNextCustomerId(customers: Customer[]): string {
    const numbers = customers
        .map((c) => parseInt(c.customerId.replace('CUS-', ''), 10))
        .filter((n) => !Number.isNaN(n))
    const max = numbers.length > 0 ? Math.max(...numbers) : 1000
    return `CUS-${max + 1}`
  }

  function handleSubmit() {
    const nextErrors = validateCustomerDraft(draft)
    setErrors(nextErrors)
    if (Object.keys(nextErrors).length > 0) return

    setSaving(true)
    if (mode.type === 'create') {
      const newCustomer: Customer = {
        customerId: getNextCustomerId(customers),
        ...draft,
      }
      setCustomers((prev) => [...prev, newCustomer])
    } else if (mode.type === 'edit') {
      const { customerId } = mode
      setCustomers((prev) =>
          prev.map((c) => (c.customerId === customerId ? { ...c, ...draft } : c)),
      )
    }
    setSaving(false)
    setMode({ type: 'list' })
  }

  function handleCancel() {
    setDraft(emptyDraft())
    setMode({ type: 'list' })
  }

  return (
    <main>
      <h1>Customer Management Platform</h1>
      <ul>
        {customers.map((c) => (
          <li key={c.customerId}>{c.fullName}</li>
        ))}
      </ul>
      {mode.type !== 'list' && (
        <CustomerForm
          draft={draft}
          errors={errors}
          saving={saving}
          onChange={setDraft}
          onSubmit={handleSubmit}
          onCancel={handleCancel}
        />
      )}
      <button type="button" onClick={() => setMode({ type: 'create' })}>
        New customer
      </button>
    </main>
  )
}
