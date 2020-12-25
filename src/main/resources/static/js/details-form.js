$(document).ready(function () {
    $('input').change(function () {
        let response;
        const building = $('.building');
        if (building.length) {
            response = transformBuilding(building);
        } else {
            const floor = $('.floor');
            if (floor.length) {
                response = transformFloor(floor);
            } else {
                response = transformRoom($('.room'));
            }
        }
        $('#location').prop('value', JSON.stringify(response));
    });
});

function transformBuilding(buildingObject) {
    let building = {};
    building["id"] = $(buildingObject).find('input[name="building-id"]').val();
    building["name"] = $(buildingObject).find('input[name="building-name"]').val();
    let floors = [];
    $(buildingObject).find('.floor').each(function (i, obj) {
        const floor = transformFloor(obj);
        floors.push(floor);
    });
    building["locations"] = floors;
    return building;
}


function transformFloor(floorObject) {
    let floor = {};
    floor["id"] = $(floorObject).find('input[name="floor-id"]').val();
    floor["name"] = $(floorObject).find('input[name="floor-name"]').val();
    let rooms = [];
    $(floorObject).find('.room').each(function (i, obj) {
        const room = transformRoom(obj);
        rooms.push(room);
    });
    floor["locations"] = rooms;
    return floor;
}

function transformRoom(roomObject) {
    let room = {};
    room["id"] = $(roomObject).find('input[name="room-id"]').val();
    room["name"] = $(roomObject).find('input[name="room-name"]').val();
    room["area"] = $(roomObject).find('input[name="room-area"]').val();
    room["cube"] = $(roomObject).find('input[name="room-cube"]').val();
    room["heating"] = $(roomObject).find('input[name="room-heating"]').val();
    room["light"] = $(roomObject).find('input[name="room-light"]').val();
    return room;
}
