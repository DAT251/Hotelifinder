# Hotelifinder

## DAT251 web app for Hotel Booking.
Hotelifinder is a web application that helps users find hotel accommodations based on their planned activities and access to public transportation. Unlike traditional hotel booking platforms, Hotelifinder prioritizes proximity to activities and promotes sustainable travel options. The application was developed with a strong focus on user-centered design and eco-friendly planning.

## How to Run Locally

### Frontend (Next.js + React)

1. Navigate to the frontend folder:
   ```bash
   cd frontend
   ```
2. Install dependencies
   ```bash
   npm install
   ```
3. Start the frontend server
   ```bash
   npm run dev
   ```

### Backend (SpringBoot)

1. Navigate to the backend folder:
   ```bash
   cd backend
   ````
2. Start the backend server
   ```bash
   mvn spring-boot:run
   ````

### After Starting Both Servers

Once both the frontend and backend servers are running:
* Open your browser at [http://localhost:3000/](http://localhost:3000/) 

## How to Use the App

1. Open your browser and go to [http://localhost:3000/](http://localhost:3000/).
2. Enter the name of the city you wish to travel to. (NOTE: Only Oslo is fully supported!)
3. Select the activities you are interested in from the available options.
4. Choose one of the following:
    * Want to live close to a specific activity?
        - Select an individual activity venue on the map to view hotels located near that specific activity
        - Select an accomondation to see information about it
        - To see travel routes to the venues from the selected accomodation, click 'Show Public Transport Routes to My Venues' 
        - Want to check out accomodations around other venues? Click 'Reset map' to select another activity venue.
   * Want to live as close to all activities as possible?
        - Choose one of the three hotel recommendations suggested by our algorithm, which finds the best location for traveling between all your selected activities using public transportation.

## Known Issues and Limitations

* As of now, you can only search for the cities: Bergen, Oslo and Trondheim.
* As of now, there is only data for Oslo. The application will not work for the other cities.
* If you select a venue with no hotels nearby, a console error will appear. Click reset map to get back to venue selection and continue to use the app.

## Project Management

The team used agile practices throughout the project, organizing development tasks and sprints using a Jira board.

[View our Jira Board here](https://dat250-gruppe3.atlassian.net/jira/software/projects/DAT251/boards/3?atlOrigin=eyJpIjoiYjkxZjY1Y2M1MTIxNGZiMGFhOWVlY2I2ODQ5YzBhNDEiLCJwIjoiaiJ9)

## Authors

This project was developed by Group B as part of the course DAT251.

**Team members:**
- Vetle M. Knutsen
- Casper B. Karlsen
- Thea Jenny E. Kolnes
- Sander C. Ødegaard
- Kristoffer E. Wågen
- Eivind Sulen
