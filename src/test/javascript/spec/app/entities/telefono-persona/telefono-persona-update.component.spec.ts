import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { TelefonoPersonaUpdateComponent } from 'app/entities/telefono-persona/telefono-persona-update.component';
import { TelefonoPersonaService } from 'app/entities/telefono-persona/telefono-persona.service';
import { TelefonoPersona } from 'app/shared/model/telefono-persona.model';

describe('Component Tests', () => {
  describe('TelefonoPersona Management Update Component', () => {
    let comp: TelefonoPersonaUpdateComponent;
    let fixture: ComponentFixture<TelefonoPersonaUpdateComponent>;
    let service: TelefonoPersonaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [TelefonoPersonaUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TelefonoPersonaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TelefonoPersonaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TelefonoPersonaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TelefonoPersona(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new TelefonoPersona();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
