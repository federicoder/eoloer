import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { AllegatoTaskUpdateComponent } from 'app/entities/allegato-task/allegato-task-update.component';
import { AllegatoTaskService } from 'app/entities/allegato-task/allegato-task.service';
import { AllegatoTask } from 'app/shared/model/allegato-task.model';

describe('Component Tests', () => {
  describe('AllegatoTask Management Update Component', () => {
    let comp: AllegatoTaskUpdateComponent;
    let fixture: ComponentFixture<AllegatoTaskUpdateComponent>;
    let service: AllegatoTaskService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [AllegatoTaskUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(AllegatoTaskUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AllegatoTaskUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AllegatoTaskService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new AllegatoTask(123);
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
        const entity = new AllegatoTask();
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
