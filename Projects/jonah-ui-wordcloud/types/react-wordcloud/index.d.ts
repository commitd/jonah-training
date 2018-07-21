declare module 'react-wordcloud' {
  export type TScale = 'sqrt' | 'log' | 'linear'
  export type TSpiral = 'archimedean' | 'rectangular'

  export type ReactWordCloudProps = {
    words: {}[]
    wordCountKey: string
    wordKey: string
    colors?: string[]
    fontFamily?: string
    height?: number
    maxAngle?: number
    maxWords?: number
    minAngle?: number
    orientations?: number
    scale?: TScale
    spiral?: TSpiral
    tooltipEnabled?: boolean
    transitionDuration?: number
    width?: number
    colorScale?: (d: {}, i: number) => string
    onSetTooltip?: (d: {}) => string
    onWordClick?: (d: {}) => void
  }

  export default class ReactWordCloud extends React.Component<ReactWordCloudProps> {}
}
