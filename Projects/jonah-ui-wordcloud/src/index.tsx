import * as React from 'react'
import * as ReactDOM from 'react-dom'
import App from './App'

import './index.css'
import 'semantic-ui-offline/semantic.min.css'

import { InvestUiPlugin } from 'invest-plugin'

ReactDOM.render(
  <InvestUiPlugin fullscreen={false}>
    <App />
  </InvestUiPlugin>,
  document.getElementById('root') as HTMLElement
)
