Markdown
# Intermodal Freight Yard Telemetry Dashboard

A full-stack simulation of a freight rail yard management system. This application is built to track train manifests, calculate freight capacities, and flag overweight loads in real-time to ensure safe and efficient logistics operations.

## Tech Stack
* **Backend:** Java, Spring Boot, Spring Web
* **Database:** Spring Data JPA, Hibernate, H2 (In-Memory SQL)
* **Frontend:** React.js, Vite
* **Testing:** JUnit 5

## Dashboard Preview

*(Add your React dashboard screenshot here: `![Dashboard UI](./docs/dashboard-ui.png)`)*

*(Add your H2 Database schema screenshot here: `![Database Schema](./docs/database-schema.png)`)*

## Core Features
* **Relational Data Mapping:** Utilizes JPA to maintain complex One-to-Many bidirectional relationships between trains and individual freight cars.
* **Algorithmic Telemetry:** Custom Spring `@Service` layer calculates live payload tonnage by aggregating foreign-key entities and flags manifests exceeding track capacity limits.
* **REST API:** Clean, stateless HTTP endpoints for creating, retrieving, and monitoring logistics data.
* **Live Operational UI:** React frontend that consumes API data to provide real-time status updates and visual warnings for dispatchers.

## How to Run Locally

### 1. Start the Java Spring Boot Backend
1. Ensure you have Java installed on your machine.
2. Open the project in your preferred IDE (IntelliJ IDEA, VS Code, or Eclipse).
3. Navigate to `src/main/java/com/spring/freightyard/FreightyardApplication.java`.
4. Run the application. The Tomcat server will start on `http://localhost:8080`.
5. *Optional:* Access the live SQL database at `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:freightdb`, User: `sa`, Password: leave blank).

### 2. Start the React Frontend
1. Open a new terminal and navigate to the frontend directory:
   ```bash
   cd freight-dashboard
Install the necessary Node dependencies:

```bash
npm install
npm run dev
```

Open your browser and navigate to http://localhost:5173 to view the dashboard.

### API Reference
**Trains**

```GET /api/trains``` - Retrieves all trains in the yard and their attached freight cars.

```GET /api/trains/{id}``` - Retrieves a specific train by its database ID.

```POST /api/trains``` - Creates a new train manifest.

```json
{
    "manifestNumber": "BNSF-OVERLOAD",
    "destination": "Chicago",
    "maxWeightCapacity": 500.0,
    "freightCars": [
        { "carNumber": "FC-01", "cargoType": "Steel", "weightInTons": 300.0 },
        { "carNumber": "FC-02", "cargoType": "Lumber", "weightInTons": 250.0 }
    ]
}
```

```GET /api/trains/{id}/status``` - Triggers the business logic service to calculate current weight vs max capacity and returns a real-time clearance status.

## Architectural Highlights
Solving the Bidirectional Relationship Trap:
When mapping JSON payloads to SQL entities via Hibernate, child entities (Freight Cars) often lose their foreign key reference to the parent (Train) if not explicitly managed. This API intercepts incoming POST requests and iterates through the List<FreightCar> to explicitly call .setTrain() before writing to the database, ensuring strict relational data integrity and preventing database orphans.