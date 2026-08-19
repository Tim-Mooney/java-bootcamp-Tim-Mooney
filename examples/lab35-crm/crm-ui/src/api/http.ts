import { ApiError } from './ApiError'

// @ts-ignore
const baseUrl = import.meta.env.VITE_API_BASE_URL as string

export async function http<T>(
  path: string,
  init: RequestInit = {},
  signal?: AbortSignal,
): Promise<T> {
  let response: Response
  try {
    response = await fetch(`${baseUrl}${path}`, { ...init, signal })
  } catch {
    throw new ApiError('Network request failed', 'network')
  }

  if (!response.ok) {
    throw new ApiError(`Request failed with status ${response.status}`, 'http')
  }

  return await (response.json() as Promise<T>)
}
