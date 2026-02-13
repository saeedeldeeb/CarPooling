# CarPooling Platform - Business Requirements Document

> Reference model: BlaBlaCar (carpooling only, buses excluded)

---

## 1. Product Vision

A community-based carpooling platform that connects drivers who have empty seats with passengers heading in the same direction. The goal is to share the journey and split travel costs — not to operate as a commercial transport service. Drivers cover fuel and tolls; passengers get affordable intercity travel.

---

## 2. User Roles

| Role | Description |
|------|-------------|
| **Passenger** | Searches for rides, books seats, pays through the platform, rates drivers |
| **Driver** | Publishes rides, accepts/rejects booking requests, receives payouts after ride completion, rates passengers |
| **Admin** | Platform administrator — manages disputes, suspensions, system configuration |

A single user can be both a driver and a passenger on different rides.

---

## 3. User Registration & Profile

### 3.1 Registration
- Sign up with email and password
- Email verification required before using the platform
- Phone number verification via SMS/OTP
- Minimum age requirement (18+)

### 3.2 Profile Information
- First name, last name
- Profile photo (mandatory for publishing rides)
- Short bio / "about me"
- Phone number (verified, hidden from other users until booking is confirmed)
- Email (verified)
- Date of birth
- Gender

### 3.3 ID Verification
- Users can upload a government-issued ID (passport or driving licence) for verification
- ID details are confidential and not visible to other users
- Verified status is shown as a badge on the profile
- ID verification is optional but strongly encouraged — verified users get more bookings

### 3.4 Preferences (visible on profile)
- **Chattiness level**: Quiet ("Bla"), Moderate ("BlaBla"), Very chatty ("BlaBlaBla")
- **Smoking**: Allowed or not allowed in the car
- **Pets**: Allowed or not allowed
- **Music**: Allowed or not allowed

---

## 4. Experience Levels & Trust

### 4.1 Experience Levels
The platform assigns one of five experience levels to each user based on their activity and profile completeness:

| Level | Criteria |
|-------|----------|
| **Newcomer** | Just registered, minimal profile |
| **Intermediate** | Profile partially completed, email verified |
| **Experienced** | Profile complete, phone verified, has a few ratings |
| **Expert** | Many completed rides, good ratings, fully verified |
| **Ambassador** | Top-tier users with extensive history, excellent ratings, full verification |

### 4.2 Level Calculation Factors
- Number of ratings received
- Profile completeness (%)
- Verification status (email, phone, ID)
- Account age / seniority
- Levels are recalculated daily

### 4.3 Trust Indicators Visible on Profile
- Experience level badge
- ID verified badge
- Email verified badge
- Phone verified badge
- Average rating (1-5 stars)
- Total number of rides completed
- Member since date

---

## 5. Vehicle Management

### 5.1 Add a Vehicle
Drivers must register at least one vehicle before publishing a ride:
- Make (e.g., Toyota)
- Model (e.g., Corolla)
- Year
- Color
- Licence plate number
- Maximum number of passengers
- Photo of the vehicle (optional but recommended)

### 5.2 Multiple Vehicles
- A driver can register multiple vehicles
- When publishing a ride, the driver selects which vehicle to use

---

## 6. Ride Publishing (Driver)

### 6.1 Creating a Ride
A driver publishes a ride by providing:

| Field | Description |
|-------|-------------|
| **Departure city** | City name + specific pickup address/point |
| **Arrival city** | City name + specific drop-off address/point |
| **Stopover points** | Optional intermediate cities along the route (with separate pricing from origin) |
| **Date and time** | Departure date and time |
| **Estimated arrival time** | Based on route |
| **Available seats** | Number of seats offered (1 to vehicle max) |
| **Price per seat** | Set by the driver; platform suggests a reasonable price based on distance, fuel cost, and wear |
| **Vehicle** | Select from registered vehicles |
| **Round trip** | Option to publish a return trip at the same time |
| **Booking mode** | Instant booking (auto-accept) or Approval required (driver manually approves each request) |
| **Ride preferences** | Chattiness, smoking, pets, music, ladies-only |
| **Baggage** | Small, medium, or large luggage accepted |
| **Additional info** | Free-text notes about the ride (e.g., "I'll be at the gas station near the highway exit") |

### 6.2 Price Rules
- The platform suggests a recommended price based on distance, fuel costs, and vehicle wear
- Drivers can adjust the price up or down but only within a fixed range set by the platform
- The price ceiling ensures drivers are sharing costs, not operating a commercial service
- Price is per seat, per trip

### 6.3 Ride Status Lifecycle

```
PUBLISHED --> IN_PROGRESS --> COMPLETED
    |
    v
 CANCELLED
```

- **PUBLISHED**: Ride is visible and bookable
- **IN_PROGRESS**: Departure time has passed, ride is happening
- **COMPLETED**: Ride has been completed, payments can be released
- **CANCELLED**: Driver cancelled the ride before departure

### 6.4 Managing a Published Ride
- Edit ride details (only while status is PUBLISHED and no confirmed bookings, or with restrictions if bookings exist)
- Cancel the ride (triggers full refund to all booked passengers)
- View list of booked passengers
- Contact passengers via in-app messaging

---

## 7. Ride Search & Discovery (Passenger)

### 7.1 Search Parameters
- **Departure city** (required)
- **Arrival city** (required)
- **Date** (required)
- **Number of passengers** (required, default 1)

### 7.2 Search Filters
| Filter | Description |
|--------|-------------|
| **Price range** | Min/max price per seat |
| **Departure time** | Time window (e.g., morning, afternoon, evening) |
| **Amenities/Preferences** | Pets allowed, smoking allowed, quiet ride |
| **Ladies only** | Show only rides published by female drivers with ladies-only enabled (available to female users) |
| **Verified drivers only** | Only show rides from ID-verified drivers |
| **Instant booking** | Only show rides that can be booked instantly without driver approval |

### 7.3 Sort Options
- **Earliest departure** (default)
- **Lowest price**
- **Shortest duration**
- **Highest driver rating**

### 7.4 Search Results Display
Each result shows:
- Driver name, profile photo, experience level, average rating
- Departure time and estimated arrival time
- Departure point and arrival point (city + address)
- Price per seat
- Available seats remaining
- Vehicle make, model, and color
- Ride preferences (chattiness, smoking, pets icons)
- Booking mode (instant or approval required)

### 7.5 Trip Alerts
- Passengers can set up alerts for a specific route and date
- When a new ride matching their criteria is published, they receive an email and push notification
- Useful when no rides are currently available for a desired route

---

## 8. Booking

### 8.1 Booking Flow — Instant Booking
1. Passenger selects a ride from search results
2. Passenger reviews ride details and driver profile
3. Passenger selects number of seats
4. Passenger selects pickup and drop-off points (from available stops or origin/destination)
5. Passenger confirms and pays through the platform
6. Booking is immediately confirmed
7. Both driver and passenger receive a confirmation notification
8. Driver and passenger can now message each other

### 8.2 Booking Flow — Approval Required
1. Steps 1-4 same as above
2. Passenger sends a booking request (no payment yet)
3. Driver receives a notification about the request
4. Driver reviews the passenger's profile and ratings
5. Driver approves or rejects the request
6. If approved: passenger is prompted to pay, booking is confirmed upon payment
7. If rejected: passenger is notified, no charge

### 8.3 Booking Status Lifecycle

```
PENDING_APPROVAL --> CONFIRMED --> COMPLETED
       |                |
       v                v
   REJECTED     CANCELLED_BY_PASSENGER
                        |
                CANCELLED_BY_DRIVER
```

### 8.4 Booking Rules
- A passenger cannot book their own ride
- A passenger cannot book the same ride twice
- Booking is only possible if there are enough available seats
- Booking is only possible if the ride status is PUBLISHED and departure is in the future

---

## 9. Cancellation & Refund Policy

### 9.1 Passenger Cancellation

| Timing | Refund |
|--------|--------|
| **More than 24 hours before departure** | Full ride amount refunded (booking/service fee is NOT refunded) |
| **Between 6 and 24 hours before departure** | 50% of ride amount refunded to passenger; 50% goes to driver |
| **Less than 6 hours before departure** | No refund; full amount goes to driver |
| **After departure time (no-show)** | No refund; full amount goes to driver |

### 9.2 Driver Cancellation
- **Full refund** to all booked passengers including the service fee
- If a driver cancels frequently, the platform reserves the right to suspend their account or restrict ride publishing

### 9.3 Driver Rejection (Approval Mode)
- If a driver does not accept a booking request, the passenger is not charged at all
- Clear confirmation is shown that no payment was taken

---

## 10. Payment & Pricing

### 10.1 Payment Flow
1. Passenger pays the full amount (ride price + service fee) at booking time
2. Funds are held by the platform (escrow)
3. After ride completion, funds are released to the driver
4. Release happens automatically 24 hours after the scheduled departure time
5. Driver receives the ride price (the amount they set); service fee stays with the platform

### 10.2 Service Fee (Platform Commission)
- Charged to the passenger on top of the ride price
- Typically 16-20% of the ride price
- Covers: platform maintenance, customer support, payment processing, insurance

### 10.3 Driver Payout
- Drivers receive 100% of the ride price they set (commission is on the passenger side)
- Payout methods: bank transfer or PayPal
- Payout is processed after ride completion (24h delay for dispute window)

### 10.4 Accepted Payment Methods
- Credit/debit cards (Visa, Mastercard)
- PayPal
- Apple Pay
- Google Pay

### 10.5 Price Display
- Passengers always see the total price (ride price + service fee) before booking
- Breakdown of ride price vs. service fee is shown transparently

---

## 11. Rating & Review System

### 11.1 How It Works
- After a ride is completed, both the driver and each passenger can rate each other
- Ratings are mutual: driver rates passenger, passenger rates driver
- Rating window: 14 days after ride completion

### 11.2 Rating Scale
- 1 to 5 stars
- Optional text review (max 500 characters)

### 11.3 Automatic Ratings
- If no review is submitted within 14 days, an automatic 5-star rating is assigned (assumes the ride went smoothly)
- Late cancellations and no-shows automatically receive a 1-star rating (first offense is exempted)

### 11.4 Rating Rules
- One rating per person per booking (a driver rates each passenger individually)
- Ratings cannot be edited after submission
- Ratings are public and visible on the user's profile
- Users can flag inappropriate reviews for admin review

### 11.5 Rating Impact
- Average rating is displayed on the user profile
- Rating contributes to experience level calculation
- Consistently low-rated users may face account restrictions

---

## 12. In-App Messaging

### 12.1 Pre-Booking Communication
- Passengers can message a driver before booking to ask questions
- Questions about: exact pickup location, luggage space, ride details, flexibility on time

### 12.2 Post-Booking Communication
- Once a booking is confirmed, driver and passenger can freely message each other
- Used for: coordinating exact meeting point, sharing real-time updates, last-minute changes

### 12.3 Messaging Rules
- All communication must happen within the platform (no sharing of personal contact info before booking)
- Phone numbers are shared only after booking is confirmed
- Messages are monitored for safety (spam, scam detection)

---

## 13. Notifications

### 13.1 Email Notifications

| Event | Recipient |
|-------|-----------|
| Booking confirmed | Driver + Passenger |
| Booking request received (approval mode) | Driver |
| Booking approved | Passenger |
| Booking rejected | Passenger |
| Booking cancelled by passenger | Driver |
| Booking cancelled by driver | All booked passengers |
| Payment held | Passenger |
| Payment released to driver | Driver |
| Payment refunded | Passenger |
| New ride matches trip alert | Passenger |
| Ride reminder (day before departure) | Driver + All passengers |
| Review reminder (after ride completion) | Driver + All passengers |
| Rating received | Rated user |

### 13.2 Push Notifications
- Same events as email
- Configurable: users can enable/disable specific notification types

### 13.3 SMS Notifications
- Booking confirmation
- Ride reminder (critical notifications only)

---

## 14. Safety & Moderation

### 14.1 User Verification Layers
1. **Email verification** — required at registration
2. **Phone verification** — required via SMS/OTP
3. **ID verification** — optional but incentivized (badge + higher experience level)
4. **Profile photo** — required for drivers, recommended for passengers

### 14.2 Ladies-Only Rides
- Female drivers can mark their ride as "Ladies Only"
- Only female passengers can book ladies-only rides
- Based on the gender specified in the user profile

### 14.3 Account Suspension
- Triggered by: frequent cancellations, consistently low ratings, reported fraud, policy violations
- Temporary or permanent based on severity
- Admin review before permanent suspension

### 14.4 Dispute Resolution
- Passengers can file a complaint within 24 hours after the ride
- Admin reviews the complaint and may issue refunds
- Both parties can provide their side of the story

### 14.5 Insurance
- Platform partners with an insurance provider to cover carpooling trips
- Covers incidents that interfere with the trip (driver no-show after booking, accidents)
- Refund + additional travel expense reimbursement in case of covered incidents

---

## 15. Platform Business Rules Summary

| Rule | Value |
|------|-------|
| Minimum user age | 18 |
| Service fee (passenger side) | 16-20% of ride price |
| Driver payout | 100% of set ride price |
| Payment hold release | 24 hours after departure |
| Full refund cancellation window | > 24 hours before departure |
| Partial refund cancellation window | 6-24 hours before departure |
| No refund cancellation window | < 6 hours before departure |
| Auto-rating (no review submitted) | 5 stars after 14 days |
| Auto-rating (late cancellation/no-show) | 1 star (first time exempted) |
| Max review length | 500 characters |
| Review window after ride | 14 days |
| Experience levels | Newcomer, Intermediate, Experienced, Expert, Ambassador |
| Level recalculation | Daily |
| Price setting | Driver sets within platform-defined range |

---

## 16. Feature Roadmap

> This is a production-grade platform built for high-load from day one. All phases are built with best practices, scalability, and resilience in mind — there are no shortcuts.

### Phase 1 — Core Platform
- User registration and profile (with Keycloak)
- Driver: publish ride with full details
- Passenger: search rides by route, date, and filters (price, time, preferences)
- Instant booking with payment hold
- Approval-required booking mode
- Full cancellation and refund policy
- Rating system (manual, 1-5 stars + automatic 14-day rule)
- Email notifications (booking, payment, cancellation, reminders)
- API Gateway with JWT authentication
- Stopover points on rides
- Experience levels
- Ladies-only rides

### Phase 2 — Engagement & Communication
- Trip alerts
- In-app messaging (pre-booking and post-booking)
- Push notifications
- SMS notifications (critical events)
- Round-trip publishing

### Phase 3 — Trust, Safety & Operations
- ID verification with document upload
- Dispute resolution system
- Admin dashboard
- Advanced analytics and monitoring
- Insurance integration
- Recurring rides (e.g., daily commute)

---

*Document based on BlaBlaCar's carpooling model. Sources: [BlaBlaCar Help Center](https://support.blablacar.com), [BlaBlaCar How It Works](https://www.thewomtravel.com/destinations/travel-news/how-blablacar-works.html), [BlaBlaCar Business Model](https://ideausher.com/blog/blabla-car-business-model-how-it-works/), [BlaBlaCar Experience Levels](https://support.blablacar.com/s/article/About-Experience-Levels-1729196960609?language=en_GB), [BlaBlaCar Cancellation Policy](https://support.blablacar.com/s/article/Carpool-cancellation-and-refund-policy-1729196970728?language=en_GB), [BlaBlaCar Service Fee](https://support.blablacar.com/s/article/Service-fee-1729197050567?language=en_GB)*
