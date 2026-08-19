# Lab 33 — Component notes

## List keys

why `customerId` is the React key?
`customerId` is the React key because it uniquely and stably identifies each customer

## A11y

how StatusBadge / form labels support screen readers?
`role="status"` so a screen reader announces the badge without the user needing to focus it directly
`htmlFor`/`id` like `<label htmlFor="email">Email</label>` screen readers announce the label text when the input receives focus
