import { PluginProps } from 'invest-plugin'
import { isEqual } from 'lodash-es'
import * as React from 'react'
import { Form, InputOnChangeData } from 'semantic-ui-react'
import DataContainer from './DataContainer'
import View from './View'

type OwnProps = {}

type Props = OwnProps & PluginProps

type State = {
  query: string
}

class App extends React.Component<Props, State> {
  state = {
    query: ''
  }

  componentWillReceiveProps(nextProps: Props) {
    if (this.props.action !== nextProps.action || !isEqual(this.props.payload, nextProps.payload)) {
      this.onAction(nextProps.action, nextProps.payload)
    }
  }

  render() {
    const { query } = this.state

    return (
      <div>
        <Form>
          <Form.Input placeholder="Search" value={query} onChange={this.handleChange} />
        </Form>
        {query && (
          <DataContainer variables={{ query: query }}>
            <View />
          </DataContainer>
        )}
      </div>
    )
  }

  private handleChange = (e: React.SyntheticEvent, d: InputOnChangeData) => this.setState({ query: d.value })

  private onAction = (action?: string, payload?: {}) => {
    // Implement to deal with new action requests
    // typically this will setState in order and then pass
    // that state as props to a subcomponent (which will
    // then respond with a )
  }
}

export default App
