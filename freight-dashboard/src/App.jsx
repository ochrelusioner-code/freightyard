import { useState, useEffect } from 'react'
import './App.css'

function App() {
  // useState holds the data once it arrives from the Java API
  const [trains, setTrains] = useState([])
  const [loading, setLoading] = useState(true)

  // useEffect runs the moment the page loads to fetch the data
  useEffect(() => {
    fetch('http://localhost:8080/api/trains')
        .then(response => response.json())
        .then(data => {
          setTrains(data)
          setLoading(false)
        })
        .catch(error => {
          console.error("Error fetching trains:", error)
          setLoading(false)
        })
  }, [])

  if (loading) return <h2>Connecting to Freight Yard...</h2>

  return (
      <div className="dashboard-container">
        <h1>BNSF Intermodal Yard Dashboard</h1>

        <div className="train-grid">
          {trains.length === 0 ? (
              <p>No trains currently in the yard.</p>
          ) : (
              trains.map(train => (
                  <div key={train.id} className="train-card">
                    <h3>Manifest: {train.manifestNumber}</h3>
                    <p><strong>Destination:</strong> {train.destination}</p>
                    <p><strong>Max Capacity:</strong> {train.maxWeightCapacity} Tons</p>
                    <button onClick={() => checkStatus(train.id)}>
                      Check Weight Status
                    </button>
                  </div>
              ))
          )}
        </div>
      </div>
  )
}

// Helper function to call your new capacity logic endpoint
function checkStatus(trainId) {
  fetch(`http://localhost:8080/api/trains/${trainId}/status`)
      .then(res => res.json())
      .then(status => {
        if (status.isOverweight) {
          alert(`WARNING: ${status.manifestNumber} is overweight! Current: ${status.currentWeight} / Max: ${status.maxCapacity}`)
        } else {
          alert(`CLEAR: ${status.manifestNumber} is good to go. Current: ${status.currentWeight} / Max: ${status.maxCapacity}`)
        }
      })
}

export default App