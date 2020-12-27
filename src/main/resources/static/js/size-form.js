$(document).ready(function () {
    $('input:radio[name=locationType]').change(function () {
        if (this.value === 'ROOM') {
            $('#rooms').slideUp();
            $('#floors').slideUp();
            $('#building-rooms').slideUp();
            $('#rooms-input').prop('required', false);
            $('#floors-input').prop('required', false);
            $('input[name=building-room]').prop('required', false);
        } else if (this.value === 'FLOOR') {
            $('#rooms').slideDown();
            $('#floors').slideUp();
            $('#building-rooms').slideUp();
            $('#rooms-input').prop('required', true);
            $('#floors-input').prop('required', false);
            $('#rooms-text').text("Podaj liczbę pokoi na piętrze");
            $('input[name=building-room]').prop('required', false);
        } else {
            $('#rooms').slideUp();
            $('#floors').slideDown();
            $('#building-rooms').slideDown();
            $('#rooms-input').prop('required', false);
            $('#floors-input').prop('required', true);
            $('input[name=building-room]').prop('required', true);
        }
    });

    $('input:radio[name=requestType]').change(function () {
        if (this.value === 'LIMIT') {
            $('#limit').slideDown();
            $('#limit-input').prop('required', true);
        } else {
            $('#limit').slideUp();
            $('#limit-input').prop('required', false);
        }
    });

    $('#floors-input').change(function () {
        let inputs = '';
        for (let i = 1; i <= $(this).val(); i++) {
            inputs += `
            <div class="form-group w-100">
                <label class="w-100">
                    Podaj liczbę pokoi na ${i} piętrze:
                    <input type="number" name="rooms-input" min="0" class="form-control"/>
                </label>
            </div>`;
        }
        $('#building-rooms').html(inputs);
    });

    $('input[name=rooms-input]').change(transformRoomInput);
    $('#building-rooms').on('change', 'input[name=rooms-input]', transformRoomInput);
});

function transformRoomInput() {
    const inputs = $('input[name=rooms-input]');
    let value = '';
    if (inputs.length > 1) {
        inputs.each(function (i, obj) {
            if (obj.parentElement.parentElement.style.display !== 'none') {
                value += obj.value + ',';
            }
        });
        value = value.slice(0, -1);
    } else {
        value = inputs.val();
    }
    $('#hidden-rooms-input').prop('value', value);
}
