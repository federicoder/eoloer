import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { UserPersonaUpdateComponent } from 'app/entities/user-persona/user-persona-update.component';
import { UserPersonaService } from 'app/entities/user-persona/user-persona.service';
import { UserPersona } from 'app/shared/model/user-persona.model';

describe('Component Tests', () => {
  describe('UserPersona Management Update Component', () => {
    let comp: UserPersonaUpdateComponent;
    let fixture: ComponentFixture<UserPersonaUpdateComponent>;
    let service: UserPersonaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [UserPersonaUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(UserPersonaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(UserPersonaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UserPersonaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new UserPersona(123);
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
        const entity = new UserPersona();
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
