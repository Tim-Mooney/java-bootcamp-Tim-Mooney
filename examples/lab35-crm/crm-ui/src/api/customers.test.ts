import { describe, expect, it, vi } from 'vitest'
import { customersApi } from './customers'


describe('customersApi', () => {
  it('uses API base URL', async () => {
    const fetchMock = vi.fn().mockResolvedValue({
      ok: true,
      json: async () => [],
    })
    vi.stubGlobal('fetch', fetchMock)

    await customersApi.list()

    const url = fetchMock.mock.calls[0][0] as string
    // @ts-ignore
    expect(url.startsWith(import.meta.env.VITE_API_BASE_URL)).toBe(true)
  })
})
