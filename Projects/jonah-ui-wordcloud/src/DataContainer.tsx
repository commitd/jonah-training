import gql from 'graphql-tag'
import { createDataContainer } from 'invest-components'

type Variables = {
  query: string
}

export interface Response {
  corpus: {
    searchDocuments: {
      hits: {
        words: {
          word: string
          count: number
        }[]
      }
    }
  }
}

const QUERY = gql`
  query CorpusInfo($query: String!) {
    corpus(id: "baleen_mongo") {
      searchDocuments(query: { content: $query }) {
        hits(size: 10) {
          words(minCount: 2) {
            word
            count
          }
        }
      }
    }
  }
`

const dataContainer = createDataContainer<Variables, Response>(QUERY)
export default dataContainer
