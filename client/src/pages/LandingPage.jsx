import React from 'react'
import NavBar from '../components/landingPage/NavBar'
import Hero from '../components/landingPage/Hero'
import About from '../components/landingPage/About'
import BestSellers from '../components/landingPage/BestSellers'
import GetStarted from '../components/landingPage/GetStarted'
import World from '../components/landingPage/World'
import Insights from '../components/landingPage/Insights'
import WhatsNew from '../components/landingPage/WhatsNew'
import Footer from '../components/landingPage/Footer'

function LandingPage() {
  return (
    <div className='bg-primary-black overflow-hidden'>
        <NavBar/>
        <Hero/>
        <About/>
        <BestSellers/>
        <GetStarted/>
        <WhatsNew/>
        <World/>
        <Insights/>
        <Footer/>
    </div>
  )
}

export default LandingPage