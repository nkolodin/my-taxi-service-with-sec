ALTER TABLE taxi_drive_info ADD COLUMN rating INTEGER CHECK ( rating > 0 and rating < 5 );