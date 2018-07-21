import { PluginProps } from 'invest-plugin'
import { isEqual } from 'lodash-es'
import * as React from 'react'

type OwnProps = {}

type Props = OwnProps & PluginProps

type State = {}

class App extends React.Component<Props, State> {
  state = {}

  componentWillReceiveProps(nextProps: Props) {
    if (this.props.action !== nextProps.action || !isEqual(this.props.payload, nextProps.payload)) {
      this.onAction(nextProps.action, nextProps.payload)
    }
  }

  render() {
    return (
      <div>
        <p>Hello world</p>
      </div>
    )
  }

  private onAction = (action?: string, payload?: {}) => {
    // Implement to deal with new action requests
    // typically this will setState in order and then pass
    // that state as props to a subcomponent (which will
    // then respond with a )
  }
}

export default App
