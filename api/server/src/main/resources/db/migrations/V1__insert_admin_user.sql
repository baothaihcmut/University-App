-- Check if the admin with a specific email does not exist, then insert
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM admins
        WHERE email = 'thaibao22042k4@gmail.com'
    ) THEN
        INSERT INTO admins (admin_id, first_name, last_name, email, password, phone_number)
        VALUES (
            uuid_generate_v4(), -- Generate a random UUID for the id
            'Thai',
            'Bao',
            'thaibao22042k4@gmail.com',
            '$2a$10$4lQWIiPF9daIeDMIsBaG5ejA5KAjT2TiS/FnRhXvM4ftHC3ftREy.',
            '0828537679'
        );
    END IF;
END $$;
