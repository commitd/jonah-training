import * as React from 'react'
import { Response } from './DataContainer'

type OwnProps = {
  data?: Response
}

type Props = OwnProps

export default class View extends React.Component<Props> {
  render() {
    const { data } = this.props

    if (!data) {
      return <p>No data</p>
    }

    const words = data.corpus.searchDocuments.hits.words

    if (words == null || words.length === 0) {
      return <p>No words found</p>
    }

    return <div>{words.map(w => <p key={w.word}>{w.word}</p>)}</div>
  }
}
