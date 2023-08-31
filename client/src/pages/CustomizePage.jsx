import React from 'react'
import Canvas from '../canvas';
import Customizer from './Customizer';
import Home from './Home';
function CustomizePage() {
  return (
    <div>
        <main className="app transition-all ease-in">
      <Home />
      <Canvas />
      <Customizer />
    </main>
    </div>
  )
}

export default CustomizePage