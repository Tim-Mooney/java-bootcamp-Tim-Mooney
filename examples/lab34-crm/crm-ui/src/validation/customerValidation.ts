import type { CustomerDraft } from '../types/customer'

export type FieldErrors = Partial<Record<keyof CustomerDraft, string>>
const VALID_STATUSES: CustomerDraft['status'][] = ['PROSPECT', 'ACTIVE', 'CLOSED']


export function validateCustomerDraft(draft: CustomerDraft): FieldErrors {
  const errors: FieldErrors = {}
  if(!draft.fullName) {
    errors.fullName = "Full name is required"
  }
  if(!draft.email){
    errors.email = "An email is required"
  }
  else if(!draft.email.includes("@")){
    errors.email = "A valid email is required"
  }
  if(!draft.status){
    errors.status = "Status is required"
  }
  else if(!VALID_STATUSES.includes(draft.status)){
    errors.status = "Status is required"
  }
  return errors
}
