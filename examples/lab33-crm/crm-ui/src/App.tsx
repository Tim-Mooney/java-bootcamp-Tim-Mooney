import { useState, useEffect } from 'react'
import { CustomerList } from './components/CustomerList'
import { CustomerForm } from './components/CustomerForm'
import { seedCustomers } from './data/seedCustomers'
import type { CustomerDraft } from './types/customer'

export default function App() {
    const [customers, setCustomers] = useState(seedCustomers)
    const [isLoading, setIsLoading] = useState(true)
    const [editingId, setEditingId] = useState<string | null>(null)
    const [draft, setDraft] = useState<CustomerDraft | null>(null)

    useEffect(() => {
        setCustomers(seedCustomers)
        setIsLoading(false)
    }, [])

    function handleEdit(customerId: string) {
        const customer = customers.find((c) => c.customerId === customerId)
        if (!customer) return
        setEditingId(customerId)
        setDraft({ fullName: customer.fullName, email: customer.email, status: customer.status })
    }

    function handleSave() {
        if (!editingId || !draft) return
        setCustomers((prev) => prev.map((c) => (c.customerId === editingId ? { ...c, ...draft } : c)))
        setEditingId(null)
        setDraft(null)
    }

    function handleCancel() {
        setEditingId(null)
        setDraft(null)
    }

    return (
        <main>
            <h1>Customer Management Platform</h1>
            {isLoading ? (
                <p>Loading customers…</p>
            ) : editingId && draft ? (
                <>
                    <CustomerForm draft={draft} onChange={setDraft} onSubmit={handleSave} />
                    <button type="button" onClick={handleCancel}>Cancel</button>
                </>
            ) : customers.length === 0 ? (
                <p>No customers yet.</p>
            ) : (
                <CustomerList customers={customers} onEdit={handleEdit} />
            )}
        </main>
    )
}