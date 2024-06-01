import React, { useEffect, useState } from 'react';
import { getProfile, getEvents } from '../services/api';

const ProfilePage = () => {
  const [profile, setProfile] = useState(null);
  const [events, setEvents] = useState([]);

  useEffect(() => {
    async function fetchData() {
      const profileData = await getProfile();
      setProfile(profileData);

      const eventData = await getEvents();
      setEvents(eventData);
    }
    fetchData();
  }, []);

  return (
    <div>
      {profile && (
        <div>
          <h1>{profile.localizedFirstName} {profile.localizedLastName}</h1>
          <p>{profile.headline}</p>
        </div>
      )}
      <h2>Events</h2>
      <ul>
        {events.map(event => (
          <li key={event.id}>{event.name}</li>
        ))}
      </ul>
    </div>
  );
};

export default ProfilePage;
